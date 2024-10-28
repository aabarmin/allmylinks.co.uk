import { BlockUpdatePropsRequest } from "@/app/hooks/block/BlockUpdatePropsRequest";
import { useAppState } from "@/app/hooks/StateProvider";
import { updateBlock } from "@/lib/blockActions";
import { Delete } from "@mui/icons-material";
import { Box, Button, IconButton, Input, LinearProgress, ListItemDecorator, Option, Select, Sheet } from "@mui/joy";
import { useEffect, useState } from "react";
import { SocialIcon } from "react-social-icons";
import { SocialNetwork, SocialNetworksBlock, SocialNetworksBlockProps } from "./SocialNetworksBlock";

function arrayCopyAndAdd<T>(arr: T[], item: T): T[] {
  return [...arr, item];
}

export function SocialNetworksBlockProperties(block: SocialNetworksBlock) {
  const { dispatch } = useAppState();
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

  const [isLoading, setLoading] = useState(false);
  const saveForm = () => {
    const props = new SocialNetworksBlockProps()
    props.networks = form['networks']
    setLoading(true);

    updateBlock(block, props).then(() => {
      setLoading(false);
      dispatch(new BlockUpdatePropsRequest(
        block,
        () => props
      ))
    });
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

      {isLoading && <LinearProgress />}

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
              <Select
                value={sn.icon}
                renderValue={(v) => {
                  if (!v) {
                    return null;
                  }
                  return (
                    <ListItemDecorator>
                      <SocialIcon network={v.value} style={{ width: '25px', height: '25px' }} />
                    </ListItemDecorator>
                  )
                }}
                onChange={(e, v) => updateSocialNetwork(sn.order, 'icon', v as string)}>
                <Option value="twitter">
                  <ListItemDecorator>
                    <SocialIcon network="twitter" style={{ width: '25px', height: '25px' }} />
                  </ListItemDecorator>
                  Twitter
                </Option>
                <Option value="instagram">
                  <ListItemDecorator>
                    <SocialIcon network="instagram" style={{ width: '25px', height: '25px' }} />
                  </ListItemDecorator>
                  Instagram
                </Option>
                <Option value="facebook">
                  <ListItemDecorator>
                    <SocialIcon network="facebook" style={{ width: '25px', height: '25px' }} />
                  </ListItemDecorator>
                  Facebook
                </Option>
              </Select>
              <Input
                sx={{
                  flexGrow: 1
                }}
                value={sn.link}
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