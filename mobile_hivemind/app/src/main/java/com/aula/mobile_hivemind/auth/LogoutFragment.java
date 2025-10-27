package com.aula.mobile_hivemind.auth;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.aula.mobile_hivemind.R;
import com.aula.mobile_hivemind.auth.LoginActivity;
import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LogoutFragment extends Fragment {
    private static final String TAG = "LogoutFragment";
    private static final String PREFS_NAME = "ProfilePrefs";
    private static final String KEY_PROFILE_IMAGE = "profile_image";
    private static final String cloudName = "djouiin10";
    private static final String uploadPreset = "Main_preset";

    private TextView usuarioLogado;
    private Button btnSair;
    private ShapeableImageView imgPerfil;

    // Permissões e launchers
    private ActivityResultLauncher<String[]> permissionLauncher;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private ActivityResultLauncher<Uri> cameraLauncher;

    private Uri currentPhotoUri;
    private SharedPreferences sharedPreferences;

    public LogoutFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, 0);
        initializeLaunchers();
        initCloudinary();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logout, container, false);

        usuarioLogado = view.findViewById(R.id.usuarioLogado);
        btnSair = view.findViewById(R.id.btnSair);
        imgPerfil = view.findViewById(R.id.imgFoto);

        // Carregar imagem salva se existir
        loadSavedProfileImage();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            usuarioLogado.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }

        btnSair.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getContext(), "Deslogado com sucesso", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish();
        });

        imgPerfil.setOnClickListener(v -> showImagePickerDialog());

        return view;
    }

    private void initializeLaunchers() {
        // Launcher para permissões
        permissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                result -> {
                    Boolean cameraGranted = result.get(Manifest.permission.CAMERA);
                    Boolean storageGranted = result.getOrDefault(
                            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                                    ? Manifest.permission.READ_MEDIA_IMAGES
                                    : Manifest.permission.READ_EXTERNAL_STORAGE,
                            false
                    );

                    if (cameraGranted != null && cameraGranted && storageGranted != null && storageGranted) {
                        // Permissões concedidas
                        Log.d(TAG, "Todas as permissões concedidas");
                    } else {
                        Toast.makeText(requireContext(), "Permissões necessárias não concedidas", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Launcher para galeria
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        if (imageUri != null) {
                            handleSelectedImage(imageUri);
                        }
                    }
                }
        );

        // Launcher para câmera
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                success -> {
                    if (success && currentPhotoUri != null) {
                        handleSelectedImage(currentPhotoUri);
                    } else {
                        Toast.makeText(requireContext(), "Foto não foi tirada", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void showImagePickerDialog() {
        // Verificar permissões primeiro
        checkPermissions();

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Escolher imagem");
        builder.setMessage("Selecione a fonte da imagem");

        builder.setPositiveButton("Câmera", (dialog, which) -> openCamera());
        builder.setNegativeButton("Galeria", (dialog, which) -> openGallery());
        builder.setNeutralButton("Cancelar", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void checkPermissions() {
        String[] permissions;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions = new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_MEDIA_IMAGES
            };
        } else {
            permissions = new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            };
        }
        permissionLauncher.launch(permissions);
    }

    private void openCamera() {
        try {
            File photoFile = createImageFile();
            if (photoFile != null) {
                currentPhotoUri = FileProvider.getUriForFile(
                        requireContext(),
                        requireContext().getPackageName() + ".provider",
                        photoFile
                );
                cameraLauncher.launch(currentPhotoUri);
            }
        } catch (IOException e) {
            Toast.makeText(requireContext(), "Erro ao criar arquivo para foto", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Erro ao criar arquivo para foto", e);
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(intent);
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        return File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
    }

    private void handleSelectedImage(Uri imageUri) {
        try {
            // Carregar e redimensionar a imagem
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
            Bitmap resizedBitmap = resizeBitmap(bitmap, 500); // Reduzir para 500px de largura máxima

            // Salvar localmente
            saveProfileImageLocally(resizedBitmap);

            // Atualizar a ImageView
            imgPerfil.setImageBitmap(resizedBitmap);

            // Upload para Cloudinary (implementar conforme sua necessidade)
            uploadToCloudinary(imageUri);

        } catch (IOException e) {
            Toast.makeText(requireContext(), "Erro ao carregar imagem", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Erro ao carregar imagem", e);
        }
    }

    private Bitmap resizeBitmap(Bitmap bitmap, int maxSize) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        if (width <= maxSize && height <= maxSize) {
            return bitmap;
        }

        float ratio = (float) width / height;
        int newWidth, newHeight;

        if (width > height) {
            newWidth = maxSize;
            newHeight = (int) (maxSize / ratio);
        } else {
            newHeight = maxSize;
            newWidth = (int) (maxSize * ratio);
        }

        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
    }

    private void saveProfileImageLocally(Bitmap bitmap) {
        // Converter Bitmap para Base64 e salvar no SharedPreferences
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PROFILE_IMAGE, encodedImage);
        editor.apply();
    }

    private void loadSavedProfileImage() {
        String encodedImage = sharedPreferences.getString(KEY_PROFILE_IMAGE, null);
        if (encodedImage != null) {
            byte[] byteArray = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            if (bitmap != null) {
                imgPerfil.setImageBitmap(bitmap);
            }
        }
    }

    private void initCloudinary() {
        try {
            Map config = new HashMap();
            config.put("cloud_name", cloudName);
            config.put("secure", true);
            MediaManager.init(requireContext(), config);
        } catch (Exception e) {
            Log.e(TAG, "Erro ao inicializar Cloudinary", e);
        }
    }

    private void uploadToCloudinary(Uri imageUri) {
        Log.d(TAG, "Upload para Cloudinary: " + imageUri.toString());
        Toast.makeText(requireContext(), "Imagem salva localmente. Upload para Cloudinary pendente.", Toast.LENGTH_SHORT).show();

        MediaManager.get().upload(imageUri)
            .option("folder", "profile_pictures")
            .unsigned(uploadPreset)
            .callback(new UploadCallback() {
                @Override
                public void onStart(String requestId) {
                    Log.d(TAG, "Upload iniciado");
                }

                @Override
                public void onProgress(String requestId, long bytes, long totalBytes) {
                    Log.d(TAG, "Upload em progresso: " + bytes + "/" + totalBytes);
                }

                @Override
                public void onSuccess(String requestId, Map resultData) {
                    Log.d(TAG, "Upload concluído com sucesso");
                    String imageUrl = (String) resultData.get("secure_url");

                    requireActivity().runOnUiThread(() -> {
                        Toast.makeText(requireContext(), "Imagem salva na nuvem!", Toast.LENGTH_SHORT).show();

                        // Carregar imagem do Cloudinary usando Glide
                        Glide.with(requireContext())
                                .load(imageUrl)
                                .into(imgPerfil);

                        // Salvar a URL para uso futuro
                        saveCloudinaryUrl(imageUrl);
                    });
                }

                @Override
                public void onError(String requestId, ErrorInfo error) {
                    Log.e(TAG, "Erro no upload: " + error.getDescription());
                }

                @Override
                public void onReschedule(String requestId, ErrorInfo error) {
                    Log.d(TAG, "Upload reagendado");
                }
            })
            .dispatch();

    }

    private void saveCloudinaryUrl(String imageUrl) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cloudinary_url", imageUrl);
        editor.apply();
    }

    private void loadCloudinaryImage() {
        String cloudinaryUrl = sharedPreferences.getString("cloudinary_url", null);
        if (cloudinaryUrl != null) {
            Glide.with(requireContext())
                    .load(cloudinaryUrl)
                    .into(imgPerfil);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (currentPhotoUri != null) {
            outState.putString("currentPhotoUri", currentPhotoUri.toString());
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            String savedUri = savedInstanceState.getString("currentPhotoUri");
            if (savedUri != null) {
                currentPhotoUri = Uri.parse(savedUri);
            }
        }
    }
}