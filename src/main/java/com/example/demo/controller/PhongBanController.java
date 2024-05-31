package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.demo.entity.LoaiPhongBan;
import com.example.demo.entity.PhongBan;
import com.example.demo.repositories.LoaiPhongBanRepository;
import com.example.demo.repositories.PhongBanRepository;
import com.example.demo.model.PhongBanModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "phong-ban")
@RequiredArgsConstructor
public class PhongBanController {

  private final PhongBanRepository phongBanRepository;

  private final LoaiPhongBanRepository loaiPhongBanRepository;

  private final Validator validator;

  private final Integer PAGE_NUMBER = 0;

  private final Integer PAGE_SIZE = 5;

  private PageRequest PAGE_REQUEST = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

  @GetMapping("/hien-thi")
  public String hienThi(
      Model model,
      @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber
  ) {
    if (pageNumber > 0) {
      PAGE_REQUEST = PageRequest.of(pageNumber - 1, this.PAGE_SIZE);
    }
    Page<PhongBan> phongBanPage = this.phongBanRepository.findAll(PAGE_REQUEST);

    model.addAttribute("phongBanPage", phongBanPage);
    return "/phong-ban/hien-thi";
  }

  @GetMapping("/hien-thi/page")
  @ResponseBody
  public Page<PhongBan> hienThi(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber) {
    return this.phongBanRepository.findAll(PageRequest.of(pageNumber, PAGE_SIZE));
  }

  @GetMapping("/remove/{id}")
  public String remove(@PathVariable(name = "id") Integer id) {
    this.phongBanRepository.deleteById(id);
    return "redirect:/phong-ban/hien-thi";
  }


  @GetMapping("/view-update/{id}")
  public String viewUpdate(Model model, @PathVariable(name = "id") Integer id) {

    PhongBan phongBan = this.phongBanRepository.findById(id)
        .orElse(new PhongBan());

    List<LoaiPhongBan> loaiPhongBanList = this.loaiPhongBanRepository.findAll();
    loaiPhongBanList.add(0, phongBan.getLoaiPhongBan());

    model.addAttribute("loaiPhongBanList", loaiPhongBanList);
    model.addAttribute(
        "phongBan",
        PhongBanModel.builder()
            .id(phongBan.getId())
            .ma(phongBan.getMa())
            .ten(phongBan.getTen())
            .soLuongPhongBan(phongBan.getSoLuongPhongBan())
            .soLuongNhanVien(phongBan.getSoLuongNhanVien())
            .trangThai(phongBan.getTrangThai())
            .build()
    );

    return "/phong-ban/update";
  }

  @PostMapping("/update/{id}")
  public String update(Model model,
                       @PathVariable(name = "id") Integer id,
                       @ModelAttribute(name = "phongBan") PhongBanModel request) {
    if (this.validate(model, request)) {

      List<LoaiPhongBan> loaiPhongBanList = this.loaiPhongBanRepository.findAll();
      loaiPhongBanList.add(
          0,
          this.loaiPhongBanRepository.findById(request.getLoaiPhongBanId())
              .orElseThrow()
      );

      model.addAttribute("loaiPhongBanList", loaiPhongBanList);
      model.addAttribute("phongBan", request);

      return "/phong-ban/update";
    }

    this.phongBanRepository.findById(id)
        .ifPresent(
            p -> {
              p.setMa(request.getMa());
              p.setTen(request.getTen());
              p.setSoLuongPhongBan(request.getSoLuongPhongBan());
              p.setSoLuongNhanVien(request.getSoLuongNhanVien());
              p.setTrangThai(request.getTrangThai());
              p.setLoaiPhongBan(
                  this.loaiPhongBanRepository.findById(request.getLoaiPhongBanId())
                      .orElseThrow()
              );

              this.phongBanRepository.save(p);
            }
        );
    return "redirect:/phong-ban/hien-thi";
  }

//  @GetMapping("/view-add")
//  public String viewAdd() {
//    return "/product/add";
//  }
//
//  @PostMapping("/add")
//  public String add(Model model,
//                    @ModelAttribute PhongBan phongBan) {
////    if (this.validate(model, giangVien)) {
////    model.addAttribute("giangVien", giangVien);
////    return "add";
////    }
//    this.productRepository.save(phongBan);
//    return "redirect:/product/hien-thi";
//  }

  private boolean validate(Model model, PhongBanModel request) {
    Map<String, List<String>> rerult = this.validator.validate(request)
        .stream()
        .collect(
            Collectors.groupingBy(
                o -> o.getPropertyPath().toString(),
                Collectors.mapping(
                    ConstraintViolation::getMessage, Collectors.toList()
                )
            )
        );

    if (!CollectionUtils.isEmpty(rerult)) {
      model.addAttribute("messageError", rerult);
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
}
