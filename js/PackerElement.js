import Condition from "./Condition.js";
import Data from "./Data.js";
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

        for (condition of conditions) {
            
        }

        Data.CONDITIONS.forEach(e => {
            const condition = document.createElement("div");
            condition.innerText = e.name;
            condition.title = e.description;
            conditions.appendChild(condition);
        })

        Data.CATEGORIES.forEach(c => {
            const category = document.createElement("div");
            category.innerText = c.name;
            category.title = c.description;
            items.appendChild(category); // could be empty
        
            Data.ITEMS.forEach(e => {
                if (e.category == c.id) {
                    const item = document.createElement("div");
                    item.innerText = e.name;
                    item.title = e.description;
                    category.appendChild(item);
                } 
            })
        });

    }
    
}