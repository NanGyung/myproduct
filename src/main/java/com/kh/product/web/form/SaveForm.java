package com.kh.product.web.form;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SaveForm {
  @NotBlank
  @Size(min=2, max=10)
  private String pname;

  @NotNull
  @Positive
  @Max(1000)
  private Long quantity;

  @NotNull
  @Positive
  @Min(1000)
  private Long price;
}
