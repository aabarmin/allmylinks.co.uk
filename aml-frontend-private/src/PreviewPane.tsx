import type { ReactNode } from 'react';
import BlockHeader from './blocks/BlockHeader';
import BlockMadeWithAml from './blocks/BlockMadeWithAml';
import type { BlockModel } from './model/BlockModel';
import type { BlockType } from './model/BlockTypeModel';
import type { PageProps } from './model/PageModel';

interface Props {
  pageBlocks: BlockModel[];
  pageProps: PageProps;
}

function getBlockByType(block: BlockModel): ReactNode {
  const type: BlockType = block.blockType.type;
  switch (type) {
    case "HEADER_BLOCK": return <BlockHeader key={block.blockId} block={block} />
    default: return <div key={block.blockId}>Пук, среньк, все еще :(</div>
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