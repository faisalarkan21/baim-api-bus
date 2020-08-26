// Call the dataTables jQuery plugin
var record = 0;
$(document).ready(function() {
    var crudServiceBaseUrl = "https://demos.telerik.com/kendo-ui/service",
    dataSource = new kendo.data.DataSource({
    transport: {
            read:  {
                url: crudServiceBaseUrl + "/Products",
                dataType: "jsonp"
            },
            update: {
                url: crudServiceBaseUrl + "/Products/Update",
                dataType: "jsonp"
            },
            destroy: {
                url: crudServiceBaseUrl + "/Products/Destroy",
                dataType: "jsonp"
            },
            create: {
                url: crudServiceBaseUrl + "/Products/Create",
                dataType: "jsonp"
            },
           parameterMap: function(options, operation) {
               if (operation !== "read" && options.models) {
                   return {models: kendo.stringify(options.models)};
               }
           }
       },
       batch: true,
       pageSize: 10,
       schema: {
           model: {
                id: "ProductID",
                fields: {
                    ProductID: { editable: false, nullable: true },
                    ProductName: { validation: { required: true } },
                    UnitPrice: { type: "number", validation: { required: true, min: 1} },
                    Discontinued: { type: "boolean" },
                    UnitsInStock: { type: "number", validation: { min: 0, required: true } }
                }
           }
       }
   });
   $("#grid").kendoGrid({
       dataSource: dataSource,
       navigatable: true,
       height: 400,
       filterable: true,
       pageable: {
           alwaysVisible: false,
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
           "ProductName",
           { field: "UnitPrice", title: "Unit Price", format: "{0:c}", width: 120 },
           { field: "UnitsInStock", title: "Units In Stock", width: 120 },
           { field: "Discontinued", width: 120, editor: customBoolEditor },
           { command: "destroy", title: "&nbsp;", width: 150 }],
       editable: true,
        dataBinding: function() {
        record = (this.dataSource.page() -1) * this.dataSource.pageSize();
        }
   });

   var grid = $("#grid").data("kendoGrid");

});
function customBoolEditor(container, options) {
    $('<input class="k-checkbox" type="checkbox" name="Discontinued" data-type="boolean" data-bind="checked:Discontinued">').appendTo(container);
}