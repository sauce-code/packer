import PackerElement from "./js/PackerElement.js";

customElements.define("packer-element", PackerElement);
const packerElement = document.createElement("packer-element");
document.body.appendChild(packerElement);