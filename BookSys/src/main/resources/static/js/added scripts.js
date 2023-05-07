var cells = document.querySelectorAll("td"); // 테이블 셀 선택 메서드
cells.forEach(function (cell) {
    if (!cell.classList.contains("occupied")) {
        cell.addEventListener("click", function () {
            this.classList.toggle("selected");
        });
    }
});

var addButton = document.querySelector("#addButton"); // 선택한 셀 예약 상태로 변경하는 메서드
addButton.addEventListener("click", function () {
    var selectedCells = document.querySelectorAll("td.selected");
    selectedCells.forEach(function (cell) {
        cell.classList.remove("selected");
        cell.classList.add("reserved");
    });
});

var removeButton = document.querySelector("#removeButton"); // 선택한 셀 예약 취소하는 메서드
removeButton.addEventListener("click", function () {
    var selectedCells = document.querySelectorAll("td.selected");
    selectedCells.forEach(function (cell) {
        cell.classList.remove("selected");
        cell.classList.remove("reserved");
    });
});