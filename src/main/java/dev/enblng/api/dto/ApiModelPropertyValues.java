package dev.enblng.api.dto;

public interface ApiModelPropertyValues {
    String ONLY_FOR_GET_MESSAGE = "Used only for get operations";
    String UUID_MESSAGE = "UUID values, must be populated for update & delete operations ";
    String FOREIGN_KEY_MESSAGE = "Foreign key, must be used for update and post operations";
}
