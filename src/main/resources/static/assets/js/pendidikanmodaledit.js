var tableBiodata = {
    create: function () {
        // jika table tersebut datatable, maka clear and dostroy
        if ($.fn.DataTable.isDataTable('#tableBiodata')) {
            //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
            $('#tableBiodata').DataTable().clear();
            $('#tableBiodata').DataTable().destroy();
        }

        $('#tableBiodata').DataTable({
            data: dataTable,
            columns: [
                {
                    title: "Jenjang",
                    data: "jenjang"
                },
                {
                    title: "Institusi",
                    data: "institusi"
                },
                {
                    title: "Tahun Masuk",
                    data: "tahunMasuk"
                },
                {
                    title: "Tahun Lulus",
                    data: "tahunLulus"
                },
                {
                    title: "Action",
                    data: null,
                    render: function (data, type, full, meta) {
                        return "<button class='bg-secondary' onclick=formBiodata.setEditData('" + meta.row + "')>Edit</button>"
                    }
                }
            ]
            // $.ajax({
            //     url: '/pendidikan',
            //     method: 'get',
            //     contentType: 'application/json',
            //     dataType: 'json',
            //     url: '/api/biodata',
            //     method: 'get',
            //     contentType: 'application/json',
            //     success: function (res, status, xhr) {
            //         if (xhr.status == 200 || xhr.status == 201) {
            //             $('#tableBiodata').DataTable({
            //                 data: [dataResult[0].pendidikanDto],
            //                 columns: [
            //                     {
            //                         title: "Jenjang",
            //                         data: "jenjang"
            //                     },
            //                     {
            //                         title: "Institusi",
            //                         data: "institusi"
            //                     },
            //                     {
            //                         title: "Tahun Masuk",
            //                         data: "tahunMasuk"
            //                     },
            //                     {
            //                         title: "Tahun Lulus",
            //                         data: "tahunLulus"
            //                     }
            // {

            //                     // }
            //                 ]
            //             });

            //         } else {

            //         }
            //     },
            //     error: function (err) {
            //         console.log(err);
            //     }
            // });
        });

    }
}
var formBiodata = {
    resetForm: function () {
        $('#form-biodata')[0].reset();
    },
    saveForm: function () {
        // if ($('#form-biodata').parsley().validate()) {
        // var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);

        // var arr = []
        // arr.push(dataResult)
        // console.log(JSON.stringify(arr), '');
        $.ajax({

            url: '/pendidikan/{id}?id=' + $('#iD').val(),
            method: 'post',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(dataTable),
            success: function (dataResult) {
                $(function () {
                    alert('status: ' + dataResult.status + '\n' + 'message: ' + dataResult.message);
                });
                $('#iD').val('');
                if ($.fn.DataTable.isDataTable('#tableBiodata')) {
                    //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
                    $('#tableBiodata').DataTable().clear();
                    $('#tableBiodata').DataTable().destroy();
                }
                dataTable = [];
            },
            error: function (err) {
                console.log(dataResult);
                //  console.log($("#idPersonn").val());
                alert("error euy");
            }

        });

    },saveTabel : function () {
        if ($.fn.DataTable.isDataTable('#tableBiodata')) {
            //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
            $('#tableBiodata').DataTable().clear();
            $('#tableBiodata').DataTable().destroy();
        }
        $.ajax({
            // url: '/pendidikan',
            // method: 'get',
            // contentType: 'application/json',
            // dataType: 'json',
            data: dataTable,
            success: function () {
                if (dataResult[0].status == 'true') {
                    $('#tableBiodata').DataTable({
                        data: dataTable,
                        columns: [
                            {
                                title: "Jenjang",
                                data: "jenjang"
                            },
                            {
                                title: "Institusi",
                                data: "institusi"
                            },
                            {
                                title: "Tahun Masuk",
                                data: "tahunMasuk"
                            },
                            {
                                title: "Tahun Lulus",
                                data: "tahunLulus"
                            }
                            // {
                            //     title: "Action",
                            //     data: null,
                            //     render: function (data, type, full, meta) {
                            //         return "<button class='btn-primary' onclick=formBiodata.setEditData('" + meta.row + "')>Edit</button>"
                            //     }
                            // }
                        ]
                    });

                } else {
                    alert('error');
                }
            },
            error: function (err) {
                console.log(err);
            }
        });

    },
    setEditData : function (row) {
        // var isi = dataTable[row];
        $('#form-edit').fromJSON(JSON.stringify(dataTable[row]));
        $('#modal-edit').modal('show');
        newrow = row;
    },

    saveEditTable : function () {
        var update = getJsonForm($("#form-edit").serializeArray(), true);
        dataTable[newrow] = update;

        $('#modal-edit').modal('hide')

        if ($.fn.DataTable.isDataTable('#tableBiodata')) {
            //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
            $('#tableBiodata').DataTable().clear();
            $('#tableBiodata').DataTable().destroy();

            // console.log('masuk if');
        }
        
    }
}
    //     formBiodata.resetForm();

    //     $.ajax({
    //         url: '/pendidikan' + idCabang,
    //         method: 'get',
    //         contentType: 'application/json',
    //         dataType: 'json',
    //         success: function (dataResult) {
    //             if (xhr.status == 200 || xhr.status == 201) {
    //                 $('#form-biodata').fromJSON(JSON.stringify(dataResult));
    //                 $('#modal-biodata').modal('show')

    //             } else {
    //                 alert('error edit');
    //             }
    //         },
    //         erorrr: function (err) {
    //             console.log(err);
    //         }
    //     });
    // }


// setEditData: function (idCabang) {
//     formBiodata.resetForm();

//     $.ajax({
//         url: '/api/biodata/' + idCabang,
//         method: 'get',
//         contentType: 'application/json',
//         dataType: 'json',
//         success: function (res, status, xhr) {
//             if (xhr.status == 200 || xhr.status == 201) {
//                 $('#form-biodata').fromJSON(JSON.stringify(res));
//                 $('#modal-biodata').modal('show')

//             } else {

//             }
//         },
//         erorrr: function (err) {
//             console.log(err);
//         }
//     });


// }

