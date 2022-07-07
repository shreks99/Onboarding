package com.shreks.onboarding.util.exception;

public enum ApplicationErrorCodes {

    REQUEST_PARAMETER_INVALID(Code.REQUEST_PARAMETER_INVALID_CODE),
    EMPTY_INPUT(Code.REQUEST_PARAMETER_INVALID_CODE),
    CONFIGURATION_NOT_FOUND(Code.CONFIGURATION_NOT_FOUND_CODE),
    INPUT_VALIDATION_FAILURE(Code.INPUT_VALIDATION_FAILURE),
    INVALID_PROGRAM_UUID(Code.INVALID_PROGRAM_UUID_CODE),
    INTERNAL_SERVER_ERROR(Code.INTERNAL_SERVER_ERROR_CODE),
    USER_NOT_FOUND(Code.USER_NOT_FOUND_CODE),
    ROLE_NOT_FOUND(Code.ROLE_NOT_FOUND_CODE),
    INVALID_MU_ID(Code.INVALID_MU_ID_CODE),
    INVALID_TEMPLATE_UUID(Code.INVALID_TEMPLATE_UUID_CODE),
    TEMPLATE_NOT_FOUND(Code.TEMPLATE_NOT_FOUND_CODE),
    INVALID_PROGRAM_SITE_TEMPLATE_UUID(Code.INVALID_PROGRAM_SITE_TEMPLATE_UUID_CODE),
    PROGRAM_SITE_TEMPLATE_ASSOCIATION_NOT_FOUND(Code.PROGRAM_SITE_TEMPLATE_NOT_FOUND_CODE),
    FEATURE_TOGGLE_NOT_ENABLED(Code.FEATURE_TOGGLE_NOT_ENABLED),
    CONSTRAIN_VIOLATION(Code.CONSTRAIN_VIOLATION),
    INVALID_TEMPLATE_TYPE(Code.INVALID_TEMPLATE_TYPE_CODE),
    TEMPLATE_HAS_ENTITIES(Code.TEMPLATE_HAS_ENTITIES_CODE),
    INVALID_LICENSE_TYPE(Code.INVALID_LICENSE_TYPE),
    INVALID_TENANT_ID(Code.INVALID_TENANT_ID);

    private final String reasonPhrase;

    ApplicationErrorCodes(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public static class Code {


        private Code() {}

        public static final String CONFIGURATION_NOT_FOUND_CODE = "CONFIGURATION_NOT_FOUND";
        public static final String REQUEST_PARAMETER_INVALID_CODE = "REQUEST_PARAMETER_INVALID";
        public static final String INTERNAL_SERVER_ERROR_CODE = "INTERNAL_SERVER_ERROR";

        public static final String INPUT_VALIDATION_FAILURE = "INPUT_VALIDATION_FAILURE";
        public static final String INVALID_PROGRAM_UUID_CODE="INVALID_PROGRAM_UUID";
        public static final String USER_NOT_FOUND_CODE = "USER_NOT_FOUND";
        public static final String ROLE_NOT_FOUND_CODE = "ROLE_NOT_FOUND";
        public static final String INVALID_MU_ID_CODE = "INVALID_MU_ID";


        public static final String INVALID_TEMPLATE_UUID_CODE="INVALID_TEMPLATE_UUID";
        public static final String TEMPLATE_NOT_FOUND_CODE = "TEMPLATE_NOT_FOUND";
        public static final String INVALID_PROGRAM_SITE_TEMPLATE_UUID_CODE="INVALID_PROGRAM_SITE_TEMPLATE_UUID";
        public static final String PROGRAM_SITE_TEMPLATE_NOT_FOUND_CODE = "PROGRAM_SITE_TEMPLATE_ASSOCIATION_NOT_FOUND";
        public static final String FEATURE_TOGGLE_NOT_ENABLED = "FEATURE_TOGGLE_NOT_ENABLED";
        public static final String CONSTRAIN_VIOLATION ="DUPLICATE KEY ENTRY";
        public static final String INVALID_TEMPLATE_TYPE_CODE="INVALID_TEMPLATE_TYPE";
        public static final String TEMPLATE_HAS_ENTITIES_CODE="TEMPLATE_HAS_ENTITIES";
        public static final String INVALID_LICENSE_TYPE = "INVALID_LICENSE_TYPE";
        public static final String INVALID_TENANT_ID = "INVALID_TENANT_ID";
    }

}