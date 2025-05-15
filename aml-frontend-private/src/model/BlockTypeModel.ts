export class BlockTypeModel {
    name: string;
    icon: string;
    type: string;
    previewComponent: string;
    configComponent: string;

    constructor(
        name: string,
        icon: string,
        type: string,
        previewComponent: string,
        configComponent: string
    ) {
        this.name = name;
        this.icon = icon;
        this.type = type;
        this.previewComponent = previewComponent;
        this.configComponent = configComponent;
    }
}