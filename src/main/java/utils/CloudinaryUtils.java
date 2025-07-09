package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryUtils {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    private Cloudinary cloudinary;

    public CloudinaryUtils() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", resourceBundle.getString("cloud_name"),
                "api_key", resourceBundle.getString("api_key"),
                "api_secret", resourceBundle.getString("api_secret")));
    }

    public Map<String, String> uploadFileStream(InputStream inputStream, String fileName) throws IOException {
        Map<String, Object> uploadParams = new HashMap<>();
        uploadParams.put("resource_type", "auto");
        uploadParams.put("public_id", "uploads/" + System.currentTimeMillis());

        Map<?, ?> uploadResult = cloudinary.uploader().uploadLarge(inputStream, uploadParams);

        Map<String, String> result = new HashMap<>();
        result.put("secure_url", (String) uploadResult.get("secure_url"));
        result.put("public_id", (String) uploadResult.get("public_id"));
        return result;
    }

    public boolean deleteFile(String publicId) {
        try {
            Map<?, ?> result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return "ok".equals(result.get("result"));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
