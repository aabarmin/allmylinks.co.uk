'use client';

import { Delete } from "@mui/icons-material";
import { Box, Button, IconButton, Input, Option, Select, Sheet } from "@mui/joy";
import { useEffect, useState } from "react";
import { SocialIcon } from "react-social-icons";
import { BlockUpdatePropsRequest } from "../hooks/block/BlockUpdatePropsRequest";
import { useAppState } from "../hooks/StateProvider";
import { Block, BlockType } from "../model/Block";

export class SocialNetwork {
  order: number;
  icon: string;
  link: string;

  constructor(order: number) {
    this.order = order;
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
  const { state, dispatch } = useAppState();
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

  const resetForm = () => {
    setFormData((f) => ({
      ...f,
      ['networks']: block.props.networks
    }))
  }

  const saveForm = () => {
    dispatch(new BlockUpdatePropsRequest(
      block,
      (p) => {
        const props = new SocialNetworksBlockProps()
        props.networks = form['networks']
        return props;
      }
    ))
  }

  const deleteSocialNetwork = (order: number) => {
    setFormData((f) => ({
      ...f,
      ['networks']: f['networks'].filter(n => n.order != order)
    }))
  }

  const updateSocialNetwork = (order: number, field: string, value: string | null) => {
    setFormData((f) => ({
      ...f,
      ['networks']: f['networks'].map(n => {
        if (n.order != order) {
          return n;
        }
        const nw = new SocialNetwork(n.order)
        nw.icon = n.icon;
        nw.link = n.link;
        if (field == 'icon') {
          nw.icon = value as string;
        } else if (field == 'link') {
          nw.link = value as string;
        } else {
          console.error('Unknown field ' + field)
        }
        return nw;
      })
    }))
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
        <Button onClick={resetForm} variant="soft">Cancel</Button>
        <Button onClick={saveForm}>Save</Button>
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
              key={sn.order}
              sx={{
                gap: 2,
                display: 'flex',
                flexDirection: 'row'
              }}>
              <Select onChange={(e, v) => updateSocialNetwork(sn.order, 'icon', v as string)}>
                <Option value="twitter">
                  <SocialIcon network="twitter" style={{ width: '25px', height: '25px' }} />
                </Option>
                <Option value="instagram">
                  <SocialIcon network="instagram" />
                </Option>
                <Option value="facebook">
                  <SocialIcon network="facebook" />
                </Option>
              </Select>
              <Input
                sx={{
                  flexGrow: 1
                }}
                onChange={(e) => updateSocialNetwork(sn.order, 'link', e.target.value)}
                placeholder="Add URL" />
              <IconButton onClick={() => deleteSocialNetwork(sn.order)}>
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
  return (
    <Box sx={{
      display: 'flex',
      flexDirection: 'row',
      justifyContent: 'center',
      gap: 2
    }}>
      {props.networks.map(n => (
        <SocialIcon network={n.icon} url={n.link} />
      ))}
    </Box>
  )
}