package ar.edu.unq.seller_user.infrastructure.web.in.grpc;

import ar.edu.unq.seller_user.SellerGrpcRequest;
import ar.edu.unq.seller_user.SellerGrpcResponse;
import ar.edu.unq.seller_user.SellerServiceGrpc;
import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.domain.port.in.seller.GetSellerUseCasePort;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class SellerServiceGrpcImpl extends SellerServiceGrpc.SellerServiceImplBase {

    private final GetSellerUseCasePort getSellerUseCasePort;

    public SellerServiceGrpcImpl(GetSellerUseCasePort getSellerUseCasePort) {
        this.getSellerUseCasePort = getSellerUseCasePort;
    }

    @Override
    public void getSellerById(SellerGrpcRequest request, StreamObserver<SellerGrpcResponse> responseObserver) {
        Seller seller = getSellerUseCasePort.getSellerById(request.getId());
        SellerGrpcResponse response = SellerGrpcResponse.newBuilder()
                .setId(seller.getId())
                .setDeleted(seller.getDeleted())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
