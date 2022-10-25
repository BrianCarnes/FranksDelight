import React from 'react'
import { alpha, Avatar, Box, Button, Card, CardContent, Divider, Grid, Modal, Stack, styled, Typography } from '@mui/material'
import { useCart } from "react-use-cart";
import StripeContainer from './StripeContainer';

const ItemWrapper = styled(Avatar)(
  ({ theme }) => `
    margin: ${theme.spacing(2, 0, 1, -0.5)};
    display: flex;
    align-items: center;
    justify-content: center;
    margin-right: ${theme.spacing(1)};
    padding: ${theme.spacing(0.5)};
    border-radius: 5px;
    height: ${theme.spacing(10)};
    width: ${theme.spacing(10)};
    background: ${
    theme.palette.mode === 'dark'
      ? theme.colors.alpha.trueWhite[30]
      : alpha(theme.colors.alpha.black[100], 0.07)
  };
  
    img {
      background: ${theme.colors.alpha.trueWhite[100]};
      padding: ${theme.spacing(0.5)};
      display: block;
      border-radius: inherit;
      height: ${theme.spacing(9.5)};
      width: ${theme.spacing(9.5)};
    }
`
);

const style = {
  position: 'absolute' as 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #333',
  boxShadow: 24,
  p: 4,
};

type Props = {
  item: item[]
}

export interface item {
  id: string,
  category: string,
  UserId: number,
  title: string,
  image: string,
  slug: string,
  summary: string,
  cooking: boolean | null,
  price: number,
  quantity: number,
  isAvailable: boolean | null,
  instructions: string,
  createdAt: string,
  updatedAt: string,
  deletedAt: string | null,
  menu: object
}

function Cart(props: Props) {
  const {
    isEmpty,
    totalUniqueItems,
    items,
    updateItemQuantity,
    removeItem,
    cartTotal
  } = useCart();
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);


  if (isEmpty) return <p>Your cart is empty</p>;

  // @ts-ignore
  return (
    <>
      <Card
        variant='outlined'
        sx={{
          height: '100%',
          width: '40em',
          display: 'flex',
          flexDirection: 'column',
          p: 3,
        }}
      >
        <CardContent>
      <Box
        display="flex"
        alignItems="center"
        justifyContent="center"
        sx={{
          pb: 3
        }}
      >
        <Typography variant="h1">Current Order: {totalUniqueItems} items</Typography>
      </Box>
      <Box
        sx={{
          display: 'flex',
          flexWrap: 'nowrap',
          justifyContent: 'flex-end',
          mb: 3
        }}
      >
        <Grid container display="flex" alignItems="center" justifyContent="center">
          <Grid xs={12} item>
            <Card>
              <CardContent
                sx={{
                  display: 'flex',
                  flexDirection: 'column',
                  height: '100%',
                  justifyContent: 'center',
                  alignItems: 'center',
                  px: 1,
                }}
              >
            <ul>
              {items?.map((item) => (
                <li key={item.id} style={{listStyle: "none"}}>
                  {item.quantity} {item.title} = ${item.price * item.quantity}
                  <Stack direction="row" spacing={2}>
                  <Button
                    size="small"
                    variant="contained"
                    onClick={() => updateItemQuantity(item.id, item.quantity? item.quantity - 1: 0)}
                    sx={{
                      ml: 1,
                      mr: 1
                    }}
                  >
                    -
                  </Button>
                  <Button
                    size="small"
                    variant="contained"
                    sx={{
                      ml: 1,
                      mr: 1
                    }}
                    onClick={() => updateItemQuantity(item.id, item.quantity? item.quantity + 1: 0)}
                  >
                    +
                  </Button>
                  <Button
                    size="small"
                    variant="contained"
                    sx={{
                      ml: 1,
                      mr: 1
                    }}
                    color="error"
                    onClick={() => removeItem(item.id)}
                  >
                    &times;
                  </Button>
                  </Stack>
                </li>
              ))}
            </ul>
            <Divider variant="middle" />
              <Typography variant="h4">SubTotal: ${cartTotal.toFixed(2)}</Typography>
              <Typography variant="h3">Total: ${(cartTotal * 1.0825).toFixed(2)}</Typography>
                <Button
                  onClick={handleOpen}
                  variant="contained"
                  color="warning"
                  size="small"
                >Proceed to Payment</Button>
                <Modal
                  open={open}
                  onClose={handleClose}
                  aria-labelledby="modal-modal-title"
                  aria-describedby="modal-modal-description"
                >
                  <Box sx={style}>
                    <StripeContainer amount={cartTotal * .0825} />
                  </Box>
                </Modal>

              </CardContent>
            </Card>
          </Grid>
        </Grid>
      </Box>
        </CardContent>
      </Card>
    </>
  );
}

export default Cart;