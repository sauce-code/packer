import Logic from "./Logic.js";

export default class PackerElement extends HTMLElement {

    logic;

    constructor() {
        super();
        this.logic = new Logic();
    }

    connectedCallback() {
        const conditions = document.createElement("div");
        conditions.id = "conditions";
        conditions.innerText = "conditions";
        this.appendChild(conditions);
        const items = document.createElement("div");
        items.id = "items";
        items.innerText = "items";
        this.appendChild(items);
    }

    
    
}