package ar.edu.unq.seller_user.infrastructure.web.in.grpc;

import ar.edu.unq.seller_user.UserGrpcRequest;
import ar.edu.unq.seller_user.UserGrpcResponse;
import ar.edu.unq.seller_user.UserServiceGrpc;
import ar.edu.unq.seller_user.domain.model.User;
import ar.edu.unq.seller_user.domain.port.in.GetUserUseCasePort;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;

@GrpcService
public class UserServiceGrpcImpl extends UserServiceGrpc.UserServiceImplBase {

    private final GetUserUseCasePort getUserUseCasePort;

    public UserServiceGrpcImpl(GetUserUseCasePort getUserUseCasePort) {
        this.getUserUseCasePort = getUserUseCasePort;
    }

    @Override
    public void getUserById(UserGrpcRequest request, StreamObserver<UserGrpcResponse> responseObserver) {
        Optional<User> optionalUser = getUserUseCasePort.getSellerById(request.getId());

        if(optionalUser.isPresent()){
            var user = optionalUser.get();

            var userGrpcResponse = UserGrpcResponse.newBuilder()
                    .setId(user.getId())
                    .setName(user.getName())
                    .setDeleted(user.getDeleted())
                    .build();

            responseObserver.onNext(userGrpcResponse);
            responseObserver.onCompleted();
        }else{
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("User with id '" + request.getId() + "' not found")
                            .asRuntimeException()
            );
        }
    }
}
