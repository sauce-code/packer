export default class Condition {

    checkbox;
    label;
    items = [];

    constructor(data, parent, items) {
        this.items = items;
        this.checkbox = document.createElement("input");
        this.checkbox.type = "checkbox";
        this.checkbox.id = data.id;
        parent.appendChild(this.checkbox);
        this.label = document.createElement("label");
        this.label.for = data.id;
        this.label.innerText = data.name;
        parent.appendChild(this.label);


        this.checkbox.addEventListener('change', function () {
            if (this.checked) {
                this.items.forEach(element => {
                    element.activate();
                });
                console.log("Checkbox is checked..");
            } else {
                console.log("Checkbox is not checked..");
            }
        });
    }



}