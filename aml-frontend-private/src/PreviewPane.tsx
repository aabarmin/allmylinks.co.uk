import type { ReactNode } from 'react';
import BlockAvatar from './blocks/BlockAvatar';
import BlockButton from './blocks/BlockButton';
import BlockHeader from './blocks/BlockHeader';
import BlockMadeWithAml from './blocks/BlockMadeWithAml';
import type { BlockResponse } from './model/BlockModel';
import type { BlockType } from './model/BlockTypeModel';
import type { PageProps } from './model/PageModel';

interface Props {
  pageBlocks: BlockResponse[];
  pageProps: PageProps;
}

function getBlockByType(block: BlockResponse): ReactNode {
  const type: BlockType = block.blockType.type;
  switch (type) {
    case "HEADER_BLOCK": return <BlockHeader key={block.blockId} block={block} />
    case "AVATAR_BLOCK": return <BlockAvatar key={block.blockId} block={block} />
    case "BUTTON_BLOCK": return <BlockButton key={block.blockId} block={block} />
    default: return <div key={block.blockId}>Пук, среньк, все еще ({block.blockType.name}) :(</div>
  }
}

export default function PreviewPane({ pageBlocks, pageProps }: Props) {
  // style={pageProps.getPageStyle()}
  // todo, abarmin: fix page props, css doesn't work here and now
  return (
    <div className="preview-pane">
      {pageBlocks.map((block) => (
        getBlockByType(block)
      ))}
      <BlockMadeWithAml />
    </div>
  );
}