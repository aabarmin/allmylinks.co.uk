interface BlockType {
    name: string;
    icon: string;
    type: string;
    previewComponent: string;
    configComponent: string;
}

interface BlocksListProps {
    availableBlocks: BlockType[];
    currentPageId: string;
}

export default function BlocksList({ availableBlocks, currentPageId }: BlocksListProps) {
    // todo, abarmin: fix link
    return (
        <ul className="list-group list-group-flush">
            {availableBlocks.map((type) => (
                <li key={type.type} className="list-group-item">
                    <i className={`bi ${type.icon}`}></i>
                    <a
                        href={`/private/dashboard/${currentPageId}/blocks-add/${type.type}`}
                        target="_self"
                    >
                        {type.name}
                    </a>
                </li>
            ))}
        </ul>
    );
}