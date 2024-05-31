package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhongBanModel {

  private Integer id;

  @NotNull(message = "Vui lòng nhập!")
  private Integer loaiPhongBanId;

  @NotBlank(message = "Vui lòng nhập!")
  private String ma;

  @NotBlank(message = "Vui lòng nhập!")
  @Column(name = "ten")
  private String ten;

  @NotNull(message = "Vui lòng nhập!")
  @Min(value = 0, message = "Phải là số nguyên dương lớn hơn 0!")
  @Column(name = "so_luong_phong_ban")
  private Integer soLuongPhongBan;

  @NotNull(message = "Vui lòng nhập!")
  @Min(value = 0, message = "Phải là số nguyên dương lớn hơn 0!")
  private Integer soLuongNhanVien;

  @NotNull(message = "Vui lòng nhập!")
  private Boolean trangThai;

}
