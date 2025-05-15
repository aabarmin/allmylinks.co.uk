interface Block {
    blockId: string;
    blockType: {
        icon: string;
        name: string;
    };
}

interface BlocksOnPageProps {
    pageBlocks: Block[];
    currentPageId: string;
}

export default function BlocksOnPage({ pageBlocks, currentPageId }: BlocksOnPageProps) {
    // todo, abarmin: fix links
    return (
        <ul className="list-group list-group-flush">
            {pageBlocks.map((block) => (
                <li key={block.blockId} className="list-group-item">
                    <i className={`bi ${block.blockType.icon}`}></i>
                    <a
                        href={`/private/dashboard/${currentPageId}/blocks/${block.blockId}`}
                        target="_self"
                    >
                        {block.blockType.name}
                    </a>
                </li>
            ))}
        </ul>
    );
}