// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ClimateService.proto

package grpc.services.climate;

public interface HvacOrBuilder extends
    // @@protoc_insertion_point(interface_extends:climate.Hvac)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 HvacId = 1;</code>
   */
  int getHvacId();

  /**
   * <code>string status = 2;</code>
   */
  java.lang.String getStatus();
  /**
   * <code>string status = 2;</code>
   */
  com.google.protobuf.ByteString
      getStatusBytes();

  /**
   * <code>int32 temperature = 3;</code>
   */
  int getTemperature();
}
