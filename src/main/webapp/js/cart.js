


function lessProducts() {
    let inputNum = document.getElementById("inputNum");
    if (inputNum.value == 1) {
    inputNum.value = 1;
    } else {
    inputNum.value--;
    }
}

function  moreProducts() {
    let inputNum = document.getElementById("inputNum");
    inputNum.value++;
};

function  onCheckAll(){
    let checkAll =  document.getElementById("checkbox__all-product");
    let checkItems = document.querySelectorAll("#checkbox__product");
    if(checkAll && checkAll.checked){
    checkItems.forEach(item => item.checked = true);
    }else{
    checkItems.forEach(item => item.checked = false);
    }
}