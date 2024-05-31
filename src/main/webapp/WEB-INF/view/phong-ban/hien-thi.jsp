<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
    <title>Hiển Thị Phòng Ban</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
<div class="container">
    <h2>QUẢN LÝ PHÒNG BAN</h2>
    <div class="mt-2">
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Mã</th>
                <th scope="col">Phòng ban</th>
                <th scope="col">Số lượng phòng ban</th>
                <th scope="col">Số lượn nhân viên</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Tên loại phòng ban</th>
                <th colspan="2">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="pb" items="${phongBanPage.content}" varStatus="var">
                <tr>
                    <th scope="row">${var.index + 1}</th>
                    <td>${pb.id}</td>
                    <td>${pb.ten}</td>
                    <td>${pb.soLuongPhongBan}</td>
                    <td>${pb.soLuongNhanVien}</td>
                    <td>${pb.trangThai?"Hoạt động":"Ngừng hoạt động"}</td>
                    <td>${pb.loaiPhongBan.ten}</td>
                    <td><button type="button" class="btn btn-primary"><a style="color: white" href="/phong-ban/view-update/${pb.id}">Update</a></button></td>
                    <td><button type="button" class="btn btn-danger"><a style="color: white" href="/phong-ban/remove/${pb.id}">Remove</a></button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="/phong-ban/hien-thi?pageNumber=${phongBanPage.number}">Previous</a></li>
                    <c:forEach var="i" begin="1" end="${phongBanPage.totalPages}">
                        <li class="page-item"><a class="page-link" href="/phong-ban/hien-thi?pageNumber=${i}">${i}</a></li>
                    </c:forEach>
                    <li class="page-item"><a class="page-link" href="/phong-ban/hien-thi?pageNumber=${phongBanPage.number + 2}">Next</a></li>
                </ul>
            </nav>
        </div>
        <button type="button" class="btn btn-primary"><a href="/phong-ban/view-add"></a>Add</button>
    </div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>

</html>
