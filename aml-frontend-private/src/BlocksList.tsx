import type { ReactNode } from "react";
import { ListGroup } from "react-bootstrap";
import { ExclamationDiamondFill, Fonts, HandThumbsUp, Link, PersonCircle } from "react-bootstrap-icons";
import type { BlockTypeModel } from "./model/BlockTypeModel";

interface Props {
    availableBlocks: BlockTypeModel[];
}

function getIconByType(type: string): ReactNode {
    switch (type) {
        case 'AVATAR_BLOCK': return <PersonCircle />
        case 'BUTTON_BLOCK': return <Link />
        case 'HEADER_BLOCK': return <Fonts />
        case 'SOCIAL_NETWORKS_BLOCK': return <HandThumbsUp />
        default: return <ExclamationDiamondFill />
    }
}

export default function BlocksList({ availableBlocks }: Props) {
    return (
        <ListGroup variant="flush">
            {availableBlocks.map((block) =>
                <ListGroup.Item key={block.type}>
                    {getIconByType(block.type)}
                    &nbsp;
                    {block.name}
                </ListGroup.Item>
            )}
        </ListGroup>
    );
}

