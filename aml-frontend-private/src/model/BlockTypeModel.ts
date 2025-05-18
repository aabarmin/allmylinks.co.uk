export type BlockType = "AVATAR_BLOCK" | "BUTTON_BLOCK" | "HEADER_BLOCK" | "SOCIAL_NETWORKS_BLOCK";

export class BlockTypeModel {
    name: string;
    icon: string;
    type: BlockType;
    previewComponent: string;
    configComponent: string;

    constructor(
        name: string,
        icon: string,
        type: BlockType,
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
