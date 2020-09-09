var formBiodata = {
    resetForm: function () {
        $('#form-biodata')[0].reset();
    },
    saveForm: function () {
        if ($('#form-biodata').parsley().validate()) {
            var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);

            $.ajax({
                url: '/person',
                method: 'post',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(dataResult),
                success: function (res, status, xhr) {
                    if (xhr.status == 200 || xhr.status == 201) {
                        tableBiodata.create();
                        $('#modal-biodata').modal('hide')

                    } else {

                    }
                },
                erorrr: function (err) {
                    console.log(err);
                }
            });
        }
    },
    getbynik : function (niK){
        if ($.fn.DataTable.isDataTable('#tableBiodata')) {
            //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
            $('#tableBiodata').DataTable().clear();
            $('#tableBiodata').DataTable().destroy();
        }
        $.ajax({
            url: '/person/'+ niK,
            method: 'get',
            contentType: 'application/json',
            dataType: 'json',
            // data: JSON.stringify(),
            success: function (result) {
                console.log(result);
                console.log(result[0].status);
                if (result[0].status=='true') {
                    $('#tableBiodata').DataTable({
                        data: [result[0].detailPendidikanDto],
                        columns: [
                            {
                                title: "NIK",
                                data: "niK"
                            }, 
                            {
                                title: "Nama",
                                data: "namA"
                            },
                            {
                                title: "Alamat",
                                data: "alamaT"
                            },
                            {
                                title: "HP",
                                data: "hp"
                            },
                            {
                                title: "Tanggal Lahir",
                                data: "tglLahir"
                            },
                            {
                                title: "Tempat Lahir",
                                data: "tmpLahir"
                            },
                            {
                                title: "Umur",
                                data: "umuR"
                            },  
                            {
                                title: "Pendidikan Terakhir",
                                data: "pendidikan_terakhir"
                            }                          
                        ]
                    });
                    alert('success!!')
                } else {
                    alert('error');

                }
            },
            erorrr: function (err) {
                console.log(err);
            }
        })


    }
};