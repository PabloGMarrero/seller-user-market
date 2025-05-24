package ar.edu.unq.seller_user.infrastructure.web.in.grpc;

import ar.edu.unq.seller_user.SellerGrpcRequest;
import ar.edu.unq.seller_user.SellerGrpcResponse;
import ar.edu.unq.seller_user.SellerServiceGrpc;
import ar.edu.unq.seller_user.domain.model.Seller;
import ar.edu.unq.seller_user.domain.port.in.seller.GetSellerUseCasePort;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;

@GrpcService
public class SellerServiceGrpcImpl extends SellerServiceGrpc.SellerServiceImplBase {

    private final GetSellerUseCasePort getSellerUseCasePort;

    public SellerServiceGrpcImpl(GetSellerUseCasePort getSellerUseCasePort) {
        this.getSellerUseCasePort = getSellerUseCasePort;
    }

    @Override
    public void getSellerById(SellerGrpcRequest request, StreamObserver<SellerGrpcResponse> responseObserver) {
        Optional<Seller> sellerOptional = getSellerUseCasePort.getSellerById(request.getId());

        if(sellerOptional.isPresent()){
            Seller seller = sellerOptional.get();

            SellerGrpcResponse sellerGrpcResponse = SellerGrpcResponse.newBuilder()
                    .setId(seller.getId())
                    .setCompanyName(seller.getCompanyName())
                    .setCompanyEmail(seller.getCompanyEmail())
                    .build();

            responseObserver.onNext(sellerGrpcResponse);
            responseObserver.onCompleted();
        }else{
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Seller with id '" + request.getId() + "' not found")
                            .asRuntimeException()
            );
        }
    }
}
