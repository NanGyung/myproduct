const add_h = e => {
    e.preventDefault();

    // const $field_errors = document.querySelectorAll('.field-error');

    // const clear = () => Array.from($field_errors).forEach(ele => ele.textContent = '');
    // //검증
    // if (pname.value.trim().length == 0) {
    //     const msg = '상품명을 입력하세요';
    //     pname.nextElementSibling.textContent = msg;
    //     pname.focus();
    //     return;
    // } else {
    //     clear();
    // }
    // if (quantity.value.trim().length == 0) {
    //     const msg = '수량을 입력하세요';
    //     quantity.nextElementSibling.textContent = msg;
    //     quantity.focus();
    //     return;
    // } else {
    //     clear();
    // }

    // const regExp = /[0-9]/;
    // if (!regExp.test(quantity.value)) {
    //     const msg = '숫자만 입력하세요';
    //     quantity.nextElementSibling.textContent = msg;
    //     quantity.focus();
    //     return;
    // } else {
    //     clear();
    // }

    // if (price.value.trim().length == 0) {
    //     const msg = '가격을 입력하세요';
    //     price.nextElementSibling.textContent = msg;
    //     price.focus();
    //     return;
    // } else {
    //     clear();
    // }

    // if (!regExp.test(price.value)) {
    //     const msg = '숫자만 입력하세요';
    //     price.nextElementSibling.textContent = msg;
    //     price.focus();
    //     return;
    // } else {
    //     clear();
    // }

    //전송
    frm.submit();
}
btnAdd.addEventListener('click', add_h, false);