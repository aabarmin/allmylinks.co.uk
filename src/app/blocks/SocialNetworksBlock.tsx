import { Delete } from "@mui/icons-material";
import { Box, Button, IconButton, Input, Option, Select, Sheet } from "@mui/joy";
import { useEffect, useState } from "react";
import { Block, BlockType } from "../model/Block";

export class SocialNetwork {
  id: number;
  icon: string;
  link: string;

  constructor(id: number) {
    this.id = id;
    this.icon = '';
    this.link = '';
  }
}

export class SocialNetworksBlockProps {
  networks: SocialNetwork[] = [];
}

export class SocialNetworksBlock implements Block<SocialNetworksBlockProps> {
  id: number;
  type: BlockType;
  order: number;
  props: SocialNetworksBlockProps;

  constructor(id: number, order: number) {
    this.id = id;
    this.order = order;
    this.type = BlockType.BLOCK_SOCIAL_NETWORKS;
    this.props = new SocialNetworksBlockProps();
  }
}

function arrayCopyAndAdd<T>(arr: T[], item: T): T[] {
  return [...arr, item];
}

export function SocialNetworksBlockProperties(block: SocialNetworksBlock) {
  const [form, setFormData] = useState({
    networks: [] as SocialNetwork[]
  });
  useEffect(() => {
    setFormData((f) => ({
      ...f,
      ['networks']: block.props.networks
    }))
  }, [block]);

  const addSocialNetwork = () => {
    const network = new SocialNetwork(form['networks'].length)

    setFormData((f) => ({
      ...f,
      ['networks']: arrayCopyAndAdd(f['networks'], network)
    }));
  }

  return (
    <Sheet sx={{
      gap: 2,
      display: 'flex',
      flexDirection: 'column'
    }}>
      <Box sx={{
        display: 'flex',
        gap: 2
      }}>
        <Button variant="soft">Cancel</Button>
        <Button>Save</Button>
      </Box>

      <Box sx={{
        display: 'flex',
        flexDirection: 'column'
      }}>
        <Button onClick={addSocialNetwork}>Add a link</Button>
      </Box>

      <Box sx={{
        gap: 2,
        display: 'flex',
        flexDirection: 'column'
      }}>
        {form['networks'].map(sn => {
          return (
            <Box
              key={sn.id}
              sx={{
                gap: 2,
                display: 'flex',
                flexDirection: 'row'
              }}>
              <Select>
                <Option value={'twitter'}>
                  X
                </Option>
              </Select>
              <Input
                sx={{
                  flexGrow: 1
                }}
                placeholder="Add URL" />
              <IconButton>
                <Delete />
              </IconButton>
            </Box>
          )
        })}
      </Box>
    </Sheet>
  )
}
export function SocialNetworksBlockComponent(props: SocialNetworksBlockProps) {
  return (<div>Block</div>)
}