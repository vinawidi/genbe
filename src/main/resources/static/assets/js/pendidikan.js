
var formBiodata = {
    resetForm: function () {
        $('#form-biodata')[0].reset();
    },
    saveForm: function () {
        if ($('#form-biodata').parsley().validate()) {
            var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);
			
			var arr = []
			arr.push(dataResult)
			console.log(JSON.stringify(arr), '');
            $.ajax({
           		
                url: '/pendidikan/{id}?id='+$('#idPersonn').val(),
                method: 'post',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(arr),
                success: function () {
                    alert("yeay");
                },
                error: function (err) {
                 console.log(dataResult);
                 console.log($("#idPersonn").val());
                    alert("error euy");
                }
            },
            
            );
            
        }
    }
};
