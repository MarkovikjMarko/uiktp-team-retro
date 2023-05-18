package uikt.uiktpteamretrobnd.web.response;

import org.springframework.stereotype.Service;

@Service
public class CustomResponse<T> {

    public CustomResponse() {
    }

    public ApiResponse<T> success(T data){
        Meta meta = new Meta(200, "Success");

        return new ApiResponse<T>(meta, data);
    }

    public ApiResponse<T> notFound(T data){
        Meta meta = new Meta(404, "Model not found");

        return new ApiResponse<T>(meta, data);
    }

    public ApiResponse<T> created(T data){
        Meta meta = new Meta(201, "Created");

        return new ApiResponse<T>(meta, data);
    }

    public ApiResponse<T> deleted(){
        Meta meta = new Meta(204, "Deleted");

        return new ApiResponse<T>(meta, null);
    }

    public ApiResponse<T> error(T errors){
        Meta meta = new Meta(204, "Errors");

        return new ApiResponse<T>(meta, errors);
    }
}
