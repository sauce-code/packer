export default class Item {

    conditions = 0;
    element;

    constructor(data, parent) {
        this.element = document.createElement("div");
        this.element.id = data.id;
        this.element.class = "item";
        this.element.innerHTML = data.name;
        this.element.title = data.description;
        this.element.style.visibility = "none";
        parent.appendChild(this.element);
    }

    activate() {
        conditions++;
        if (this.conditions == 1) {
            this.element.style.visibility = "visible";
        }
    }

    deactivate() {
        this.conditions--;
        if (this.conditions == 0) {
            this.element.style.visibility = "none";
        }
    }

}