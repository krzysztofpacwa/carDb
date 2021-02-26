let editIdError;
let editMarkError;
let editModelError;
let editDateError;
function validateEditCar() {

    let idEditValue = $('#idEdit').val();
    if (idEditValue.length === 0) {
        $('#idEditMessage').show();
        editIdError = true;
    } else {
        $('#idEditMessage').hide();
        editIdError = false;
    }

    let markEditValue = $('#markEdit').val();
    if (markEditValue.length === 0) {
        $('#markEditMessage').show();
        editMarkError = true;
    } else {
        $('#markEditMessage').hide();
        editMarkError = false;
    }

    let modelEditValue = $('#modelEdit').val();
    if (modelEditValue.length === 0) {
        $('#modelEditMessage').show();
        editModelError = true;
    } else {
        $('#modelEditMessage').hide();
        editModelError = false;
    }

    let dateEditValue = $('#dateEdit').val();
    if (dateEditValue.length === 0) {
        $('#dateEditMessage').show();
        editDateError = true;
    } else if (!(isValidDate(dateEditValue))) {
        $('#dateEditMessage').html
        ("**should be date format: yyyy-mm-dd");
        $('#dateEditMessage').css("color", "red");
        editDateError = true;
    } else {
        $('#dateEditMessage').hide();
        editDateError = false;
    }
    return !(editIdError ||editMarkError || editModelError || editDateError );
}

let addMarkError;
let addModelError;
let addDateError;
function validateAddCar() {

    let modelAddValue = $('#markAddCar').val();
    if (modelAddValue.length === 0) {
        $('#markAddMessage').show();
        addMarkError = true;
    } else {
        $('#markAddMessage').hide();
        addMarkError = false;
    }

    let markAddValue = $('#modelAddCar').val();
    if (markAddValue.length === 0) {
        $('#modelAddMessage').show();
        addModelError = true;
    } else {
        $('#modelAddMessage').hide();
        addModelError = false;
    }

    let dateAddValue = $('#dateAddCar').val();
    if (dateAddValue.length === 0) {
        $('#dateAddMessage').show();
        addDateError = true;
    } else if (!(isValidDate(dateAddValue))) {
        $('#dateAddMessage').html
        ("**should be date format: yyyy-mm-dd");
        $('#dateAddMessage').css("color", "red");
        addDateError = true;
    } else {
        $('#dateAddMessage').hide();
        addDateError = false;
    }
    return !(addMarkError || addModelError || addDateError);
}

let fromFilterbyDateError;
let toFilterbyDateError;
function validateFilterByDate() {
    let fromFilterByDate = $('#fromDateFilterByDate').val();
    if (fromFilterByDate.length === 0) {
        $('#fromDateFilterByDateMessage').show();
        fromFilterbyDateError = true;
    } else if (!(isValidDate(fromFilterByDate))) {
        $('#fromDateFilterByDateMessage').html
        ("**should be date format: yyyy-mm-dd");
        $('#fromDateFilterByDateMessage').css("color", "red");
        fromFilterbyDateError = true;
    } else {
        $('#fromDateFilterByDateMessage').hide();
        fromFilterbyDateError = false;
    }
    let toFilterByDate = $('#toDateFilterByDate').val();
    if (toFilterByDate.length === 0) {
        $('#toDateFilterByDateMessage').show();
        toFilterbyDateError = true;
    } else if (!(isValidDate(toFilterByDate))) {
        $('#toDateFilterByDateMessage').html
        ("**should be date format: yyyy-mm-dd");
        $('#toDateFilterByDateMessage').css("color", "red");
        toFilterbyDateError = true;
    } else {
        $('#toDateFilterByDateMessage').hide();
        toFilterbyDateError = false;
    }

    return !(fromFilterbyDateError || toFilterbyDateError);
}

$('#submitFilterByDate').click(function () {
    return validateFilterByDate();
});

$('#submitEdit').click(function () {
    return validateEditCar();
});

$('#submitAdd').click(function () {
    return validateAddCar();
});

$('#toggleAddModal').click(function () {
    $('#markAddMessage').hide();
    $('#modelAddMessage').hide();
    $('#dateAddMessage').hide();
});

$('#toggleByDateModal').click(function () {
    $('#fromDateFilterByDateMessage').hide();
    $('#toDateFilterByDateMessage').hide();
});

$('#toggleEditModal').click(function () {
    $('#idEditFieldMessage').hide();
    $('#valueEditFieldMessage').hide();
});