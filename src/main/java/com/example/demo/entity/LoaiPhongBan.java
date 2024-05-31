package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loai_phong_ban")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoaiPhongBan {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "ma")
  private String ma;

  @Column(name = "ten")
  private String ten;

  @Column(name = "so_luong")
  private Integer soLuong;

  @Column(name = "ghi_chu")
  private String ghiChu;

  @Column(name = "trang_thai")
  private Boolean trangThai;

  @Column(name = "ngay_tao")
  private LocalDateTime ngayTao;

  @Column(name = "ngay_sua")
  private LocalDateTime ngaySua;

  @OneToMany(mappedBy = "loaiPhongBan", cascade = CascadeType.ALL)
  private List<PhongBan> phongBanList;
}
