package com.kh.product.web.form;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SaveForm {
  @NotBlank
  private String pname;

  @NotNull
  @Positive
  private Long quantity;

  @NotNull
  @Positive
  @Min(1000)
  private Long price;
}
