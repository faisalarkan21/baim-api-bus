// Call the dataTables jQuery plugin
var record = 0;
$(document).ready(function() {
    dataSource = new kendo.data.DataSource({
        transport: {
            read:  {
                url: "/api/getAllTrips",
                dataType: "json",
                type: "POST",
                contentType: "application/json"
            },
            update: {
                url: "/api/updateTrips",
                dataType: "json",
                type: "POST",
                contentType: "application/json"
            },
            destroy: {
                url: "/api/deleteTrips",
                dataType: "json",
                type: "POST",
                contentType: "application/json"
            },
            create: {
                url: "/api/addTrips",
                dataType: "json",
                type: "POST",
                contentType: "application/json"
            },
            parameterMap: function(options, operation) {
                if (operation !== "read" && options.models) {
                    return kendo.stringify(options.models);
                }
            }
        },
        batch: true,
        pageSize: 10,
        schema: {
            model: {
                id: "id",
                fields: {
//                    id: { editable: false, nullable: true },
                    bus: { defaultValue: { id: '', code: ''}, validation: { required: true} },
                    stop: { defaultValue: { id: '', name: ''}, validation: { required: true } },
                    stopDestination: { defaultValue: { id: '', name: ''},  validation: { required: true } },
                    journeyTime: { type: "number", validation: { required: true, min: 0} },
                    fare: { type: "number", validation: { required: true, min: 0} },
                }
            }
        },
        sort: {
            field: "code",
            dir: "asc"
        }
   });
   $("#grid").kendoGrid({
        dataSource: dataSource,
        navigatable: true,
        height: 400,
        filterable: true,
        sortable: true,
        pageable: {
            alwaysVisible: true,
            pageSizes: [5, 10, 20, 100]
        },
        toolbar: ["create", "save", "cancel"],
        columns: [
            {
                title: "#",
                template: "#=++record #",
                attributes: {
                   style: "text-align: center;"
                },
                headerAttributes: {
                   style: "text-align: center;"
                },
                width: 50
            },
            {
                field: "bus",
                width: 200,
                title:"Bus Code",
                template: "#=bus.code#",
                editor: busDropDownEditor
            },
            {
                field: "stop",
                title: "Source Stop",
                // format: "{0:c}",
                width: 150,
                template: "#=stop.name#",
                editor: stopDropDownEditor,
                attributes: {
                    style: "text-align: center;"
                },
                headerAttributes: {
                    style: "text-align: center;"
                }
            },
            {
                field: "stopDestination",
                title: "Destination Stop",
                width: 150,
                template: "#=stopDestination.name#",
                editor: stopDropDownEditor,
                attributes: {
                    style: "text-align: center;"
                },
                headerAttributes: {
                    style: "text-align: center;"
                }
            },
            {
                field: "journeyTime",
                title: "Duration",
                width: 150,
                attributes: {
                     style: "text-align: center;"
                },
                headerAttributes: {
                     style: "text-align: center;"
                }
            },
            {
                field: "fare",
                title: "Fare",
                width: 150,
                attributes: {
                      style: "text-align: center;"
                },
                headerAttributes: {
                      style: "text-align: center;"
                }
            },
            {
                command: "destroy",
                title: "Action",
                width: 150,
                filterable: false,
                attributes: {
                    style: "text-align: center;"
                },
                headerAttributes: {
                    style: "text-align: center;"
                }
            }
        ],
        editable: true,
        dataBinding: function() {
            record = (this.dataSource.page() -1) * this.dataSource.pageSize();
        }
   });

   var grid = $("#grid").data("kendoGrid");

});

function busDropDownEditor(container, options) {
$('<input required name="' + options.field + '"/>')
   .appendTo(container)
   .kendoDropDownList({
       autoBind: false,
       dataTextField: "code",
       dataValueField: "id",
       dataSource: {
           transport: {
               read: {
                 url: "/api/getAllBus",
                 dataType: "json",
                 type: "POST",
                 contentType: "application/json"
             }
           }
       }
   });
}

function stopDropDownEditor(container, options) {
$('<input required name="' + options.field + '"/>')
   .appendTo(container)
   .kendoDropDownList({
       autoBind: false,
       dataTextField: "name",
       dataValueField: "id",
       dataSource: {
           transport: {
               read: {
                 url: "/api/getAllStop",
                 dataType: "json",
                 type: "POST",
                 contentType: "application/json"
             }
           }
       }
   });
}