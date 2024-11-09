import { Block } from "./Block";

export class Page {
  id: number;
  title: string;
  blocks: Block<object>[] = [];

  constructor(id: number, title: string) {
    this.id = id;
    this.title = title;
  }

  public getBlock(blockId: number): Block<object> | undefined {
    const filtered = this.blocks.filter(b => b.id == blockId);
    if (filtered.length == 0) {
      return undefined;
    }
    return filtered[0];
  }

  public withBlock(block: Block<object>): Page {
    const newPage = new Page(this.id, this.title);
    newPage.blocks = [...this.blocks, block];
    return newPage;
  }

  public withUpdatedBlock<T extends object>(block: Block<T>, callback: (current: T) => T): Page {
    return this.clone(newPage => {
      newPage.blocks = this.blocks.map(b => {
        if (b.id !== block.id) {
          return b;
        }
        // this is a dirty hack but I don't care now
        const newBlock = {} as Block<T>;
        const props = b.props as T
        newBlock.id = b.id;
        newBlock.order = b.order;
        newBlock.type = b.type;
        newBlock.props = callback(props);
        return newBlock;
      });
      return newPage;
    });
  }

  public withBlockMovedUp(block: Block<object>): Page {
    return this.withBlockMoved(block, "up");
  }

  public withBlockMovedDown(block: Block<object>): Page {
    return this.withBlockMoved(block, "down");
  }

  private withBlockMoved(block: Block<object>, direction: "up" | "down"): Page {
    return this.clone(newPage => {
      const index = this.blocks.findIndex(b => b.id === block.id);
      if (index === -1) {
        return newPage;
      }
      const newIndex = direction === "up" ? index - 1 : index + 1;
      if (newIndex < 0 || newIndex >= this.blocks.length) {
        return newPage;
      }
      const newBlocks = [...this.blocks];
      const [movedBlock] = newBlocks.splice(index, 1);
      newBlocks.splice(newIndex, 0, movedBlock);
      newBlocks.forEach((b, i) => b.order = i + 1);
      newPage.blocks = newBlocks;
      return newPage;
    });
  }

  private clone(callback: (page: Page) => Page): Page {
    const newPage = new Page(this.id, this.title);
    newPage.blocks = this.blocks;
    return callback(newPage);
  };
}