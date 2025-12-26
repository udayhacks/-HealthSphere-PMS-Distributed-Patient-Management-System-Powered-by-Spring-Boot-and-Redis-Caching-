package com.pm.billingService.grpc;

import billing.BillRequest;
import billing.BillResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@GrpcService
public class BillingGrpcService  extends BillingServiceImplBase {


    private  static  final Logger log =  LoggerFactory.getLogger(BillingGrpcService.class);



    @Override
    public  void createBillingAccount(BillRequest billRequest, StreamObserver<BillResponse> responseObserver){
        log.info("createBillingAccount request recived {}", billRequest.toString());
        ///  business logic : save to database

        BillResponse response = BillResponse.newBuilder()
                .setAccountId("1234")
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(response); // send response to client that prepared we call mutilple as we want ;
        responseObserver.onCompleted();
    }


}
