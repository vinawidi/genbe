var tableBiodata = {
    create: function () {
        // jika table tersebut datatable, maka clear and dostroy
        if ($.fn.DataTable.isDataTable('#tableBiodata')) {
            //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
            $('#tableBiodata').DataTable().clear();
            $('#tableBiodata').DataTable().destroy();
        }

        $.ajax({
            url: '/person/all',
            method: 'get',
            contentType: 'application/json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    $('#tableBiodata').DataTable({
                        data: res,
                        // alert('success!!')
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
                                title: "Nomor HP",
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
                                title: "Action",
                                data: null,
                                render: function (data, type, row) {
                                    return "<button class='bg-gray' onclick=formBiodata.setEditData('" + data.iD + "')>Edit</button>"
                                }
                            }
                        ]
                    });

                } else {
                    // alert('error')
                }
            },
            error: function (err) {
                console.log(err);
            }
        });


    }
};

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
                success: function (hasil) {
                    tableBiodata.create();
                    $('#modal-biodata').modal('hide')
                    alert('status: ' + hasil.status + '\n' + 'message: ' + hasil.message);
                },
                erorrr: function (err) {
                    console.log(err);
                }
            });
        }
    }, setEditData: function (iD) {
        console.log(iD)
        formBiodata.resetForm();

        $.ajax({
            url: '/person/detail/' + iD,
            method: 'get',
            contentType: 'application/json',
            dataType: 'json',
            success: function (res, status, xhr) {
                console.log(res)
                if (xhr.status == 200 || xhr.status == 201) {
                    $('#form-biodata').fromJSON(JSON.stringify(res));
                    $('#modal-biodata').modal('show')

                } else {

                }
            },
            erorrr: function (err) {
                console.log(err);
            }
        });

        // return res
    },
    saveEdit: function () {
        if ($('#form-biodata').parsley().validate()) {
            var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);
            dataResult.iD = parseInt(dataResult.iD)
            dataResult.idBio = parseInt(dataResult.idBio)
            console.log(dataResult)
            $.ajax({
                url: '/person',
                method: 'post',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(dataResult),
                success: function (hasil2) {

                    tableBiodata.create();
                    $('#modal-biodata').modal('hide')
                    if (hasil2.status == 'true') {
                        // console.log(hasil2);
                        Swal.fire({
                            icon: 'success',
                            title: hasil2.status + '\n' + hasil2.message
                        })
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: hasil2.status + '\n' + hasil2.message
                        })
                    }
                    // location.reload('true');

                },
                error: function (err) {
                    alert(err.message),
                    console.log(err);
                }
            });
        }
    }

};
