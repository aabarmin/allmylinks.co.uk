import React from 'react';
import BlockMadeWithAml from './blocks/BlockMadeWithAml';

interface PageProps {
    getPageStyle: () => React.CSSProperties;
}

interface Block {
    blockType: {
        previewComponent: string;
    };
}

interface PreviewPaneProps {
    pageProps: PageProps;
    pageBlocks: Block[];
}

export default function PreviewPane({ pageProps, pageBlocks }: PreviewPaneProps) {
    return (
        <div className="preview-pane" style={pageProps.getPageStyle()}>
            {pageBlocks.map((block, index) => {
                const PreviewComponent = React.lazy(() =>
                    import(`./blocks/${block.blockType.previewComponent}`)
                );
                return (
                    <React.Suspense fallback={<div>Loading...</div>} key={index}>
                        <PreviewComponent block={block} />
                    </React.Suspense>
                );
            })}

            <BlockMadeWithAml />
        </div>
    );
}