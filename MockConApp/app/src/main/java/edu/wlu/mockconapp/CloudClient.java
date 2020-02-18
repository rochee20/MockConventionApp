///**
// * A class for interacting with cloud services
// *
// * Copyright (C) 2019 Simon D. Levy, Robert Hageboeck
// */
//package edu.wlu.mockconapp;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.amazonaws.mobile.client.AWSMobileClient;
//import com.amazonaws.mobile.client.AWSStartupHandler;
//import com.amazonaws.mobile.client.AWSStartupResult;
//import com.amazonaws.mobile.config.AWSConfiguration;
//import com.amazonaws.auth.AWSCredentialsProvider;
//import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
//import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
//import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
//
//
//public class CloudClient {
//
//    // Our connection to AWS
//    private DynamoDBMapper dynamoDBMapper;
//
//    // For now, just implement contents as a circular queue of strings
//    private static int MAX_ITEMS = 10;
//    private SessionsDO [] _sessions;
//    private int _sessionCount;
//
//    public CloudClient(Context context) {
//
//        AWSMobileClient.getInstance().initialize(context, new AWSStartupHandler() {
//            @Override
//            public void onComplete(AWSStartupResult awsStartupResult) {
//                Log.d("DEBUG", "AWSMobileClient is instantiated and you are connected to AWS!");
//            }
//        }).execute();
//
//        AWSCredentialsProvider credentialsProvider = AWSMobileClient.getInstance().getCredentialsProvider();
//
//        AWSConfiguration configuration = AWSMobileClient.getInstance().getConfiguration();
//
//        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);
//
//        dynamoDBMapper = DynamoDBMapper.builder()
//                .dynamoDBClient(dynamoDBClient)
//                .awsConfiguration(configuration)
//                .build();
//
//        _sessions = new SessionsDO[MAX_ITEMS];
//        _sessionCount = 0;
//    }
//
//    public  void load() {
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                PaginatedScanList paginatedScanList = dynamoDBMapper.scan(SessionsDO.class, new DynamoDBScanExpression());
//
//                for (Object o : paginatedScanList) {
//                    SessionsDO sessionsDO = (SessionsDO)o;
//
//                    // Eventually, session IDs should be integers, not doubles, so we won't have to cast them
//                    double id = sessionsDO.getId();
//
//                    // For now, use a circular buffer
//                    int index = (int)(id-1)  % MAX_ITEMS;
//
//                    _sessions[index] = sessionsDO;
//                    if ((index+1) > _sessionCount) _sessionCount++;
//
//                    //Log.d("DEBUG", "session: " + index);
//                }
//
//            }
//        }).start();
//    }
//
//    public SessionsDO getSession(int id) {
//        return _sessions[id];
//    }
//}
