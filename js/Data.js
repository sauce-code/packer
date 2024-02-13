export default class Data {

    static CATEGORIES = [
        {
            id: "#allgemein",
            name: "Allgemein",
            description: "Allgemeine Gegenstände, die man auf jeder Packliste haben möchte."
        },
        {
            id: "#kleidung",
            name: "Kleidung",
            description: "Kleidung zum Anziehen. Sollte niemals fehlen."
        }
    ]

    static CONDITIONS = [
        {
            id: "#winter",
            name: "Winter",
            description: "Diese eine kalte Jahreszeit. Erforert entsprechende Anpassungen beim Packen."
        }
    ]

    static ITEMS = [
        {
            id: "#handy",
            name: "Handy + Ladekabel",
            category: "#allgemein",
            conditions: [],
            description: "Der Grundstein jeglicher Kommunikation. Ohne Ladekabel schnell wertlos."
        },
        {
            id: "#muetze",
            name: "Mütze",
            category: "#kleidung",
            conditions: ["#winter"],
            description: "Kommt auf den Kopf. Hält warm. Ziemlich geil."
        }
    ]

}