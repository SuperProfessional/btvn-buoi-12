package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "phong_ban")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhongBan {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "loai_phong_ban_id")
  private LoaiPhongBan loaiPhongBan;


//  @NotBlank(message = "Vui lòng nhập!")
  @Column(name = "ma")
  private String ma;

//  @NotBlank(message = "Vui lòng nhập!")
  @Column(name = "ten")
  private String ten;

//  @NotNull(message = "Vui lòng nhập!")
//  @Min(value = 0, message = "Phải là số nguyên dương lớn hơn 0!")
  @Column(name = "so_luong_phong_ban")
  private Integer soLuongPhongBan;

//  @NotNull(message = "Vui lòng nhập!")
//  @Min(value = 0, message = "Phải là số nguyên dương lớn hơn 0!")
  @Column(name = "so_luong_nhan_vien")
  private Integer soLuongNhanVien;

  @Column(name = "ghi_chu")
  private String ghiChu;

//  @NotNull(message = "Vui lòng nhập!")
  @Column(name = "trang_thai")
  private Boolean trangThai;

  @Column(name = "ngay_tao")
  private LocalDateTime ngayTao;

  @Column(name = "ngay_chinh_sua")
  private LocalDateTime ngaySua;
}
