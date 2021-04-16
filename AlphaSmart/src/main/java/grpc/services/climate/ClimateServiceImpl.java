// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: climateControl.proto

package grpc.services.climate;

public final class ClimateServiceImpl {
  private ClimateServiceImpl() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_climate_Hvac_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_climate_Hvac_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_climate_HvacStatusRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_climate_HvacStatusRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_climate_HvacResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_climate_HvacResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_climate_TempRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_climate_TempRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_climate_CoLevel_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_climate_CoLevel_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_climate_CoLevelRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_climate_CoLevelRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_climate_ExtractionResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_climate_ExtractionResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024climateControl.proto\022\007climate\";\n\004Hvac\022" +
      "\016\n\006HvacId\030\001 \001(\005\022\016\n\006status\030\002 \001(\t\022\023\n\013tempe" +
      "rature\030\003 \001(\005\"#\n\021HvacStatusRequest\022\016\n\006Hva" +
      "cId\030\001 \001(\005\"+\n\014HvacResponse\022\033\n\004hvac\030\001 \001(\0132" +
      "\r.climate.Hvac\"2\n\013TempRequest\022\016\n\006HvacId\030" +
      "\001 \001(\005\022\023\n\013temperature\030\002 \001(\005\"*\n\007CoLevel\022\020\n" +
      "\010building\030\001 \001(\t\022\r\n\005CoNow\030\002 \001(\005\"\"\n\016CoLeve" +
      "lRequest\022\020\n\010building\030\001 \001(\t\"7\n\022Extraction" +
      "Response\022!\n\007coLevel\030\001 \001(\0132\020.climate.CoLe" +
      "vel2\332\001\n\016ClimateService\022E\n\016showHvacStatus" +
      "\022\032.climate.HvacStatusRequest\032\025.climate.H" +
      "vacResponse\"\000\022>\n\013setHvacTemp\022\024.climate.T" +
      "empRequest\032\025.climate.HvacResponse\"\0000\001\022A\n" +
      "\007checkCO\022\027.climate.CoLevelRequest\032\033.clim" +
      "ate.ExtractionResponse\"\000B-\n\025grpc.service" +
      "s.climateB\022ClimateServiceImplP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_climate_Hvac_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_climate_Hvac_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_climate_Hvac_descriptor,
        new java.lang.String[] { "HvacId", "Status", "Temperature", });
    internal_static_climate_HvacStatusRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_climate_HvacStatusRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_climate_HvacStatusRequest_descriptor,
        new java.lang.String[] { "HvacId", });
    internal_static_climate_HvacResponse_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_climate_HvacResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_climate_HvacResponse_descriptor,
        new java.lang.String[] { "Hvac", });
    internal_static_climate_TempRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_climate_TempRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_climate_TempRequest_descriptor,
        new java.lang.String[] { "HvacId", "Temperature", });
    internal_static_climate_CoLevel_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_climate_CoLevel_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_climate_CoLevel_descriptor,
        new java.lang.String[] { "Building", "CoNow", });
    internal_static_climate_CoLevelRequest_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_climate_CoLevelRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_climate_CoLevelRequest_descriptor,
        new java.lang.String[] { "Building", });
    internal_static_climate_ExtractionResponse_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_climate_ExtractionResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_climate_ExtractionResponse_descriptor,
        new java.lang.String[] { "CoLevel", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
