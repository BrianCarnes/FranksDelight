import {
  Typography,
  Card,
  CardHeader,
  Divider,
  List,
  ListItemAvatar,
  ListSubheader,
  ListItemText,
  Avatar,
  useTheme,
  styled, ListItemButton,
} from '@mui/material'

const ListWrapper = styled(List)(
  () => `
      .MuiListItem-root {
        border-radius: 0;
        margin: 0;
      }
`
);

function PopularTags() {
  const theme = useTheme();

  return (
    <Card sx={{ height: '100%' }}>
      <CardHeader title="Popular Tags" />
      <Divider />
      <ListWrapper disablePadding>
        <ListItemButton
          sx={{
            color: `${theme.colors.primary.main}`,
            '&:hover': { color: `${theme.colors.primary.dark}` }
          }}
        >
          <ListItemText primary="#HTML" />
        </ListItemButton>
        <Divider />
        <ListItemButton
          sx={{
            color: `${theme.colors.primary.main}`,
            '&:hover': { color: `${theme.colors.primary.dark}` }
          }}
        >
          <ListItemText primary="#software_development" />
        </ListItemButton>
        <Divider />
        <ListItemButton
          sx={{
            color: `${theme.colors.primary.main}`,
            '&:hover': { color: `${theme.colors.primary.dark}` }
          }}
        >
          <ListItemText primary="#TrendingInfuencers" />
        </ListItemButton>
        <Divider />
        <ListItemButton
          sx={{
            color: `${theme.colors.primary.main}`,
            '&:hover': { color: `${theme.colors.primary.dark}` }
          }}
        >
          <ListItemText primary="#investorsWatch2022" />
        </ListItemButton>
        <Divider />
        <ListSubheader>
          <Typography sx={{ py: 1.5 }} variant="h4" color="text.primary">
            Groups
          </Typography>
        </ListSubheader>
        <Divider />
        <ListItemButton>
          <ListItemAvatar>
            <Avatar
              sx={{
                width: 38,
                height: 38,
                background: `${theme.colors.info.main}`,
                color: `${theme.palette.info.contrastText}`
              }}
            >
              WD
            </Avatar>
          </ListItemAvatar>
          <ListItemText
            primaryTypographyProps={{
              variant: 'h5',
              color: `${theme.colors.alpha.black[100]}`
            }}
            primary="Web Designers Lounge"
          />
        </ListItemButton>
        <Divider />
        <ListItemButton>
          <ListItemAvatar>
            <Avatar
              sx={{
                width: 38,
                height: 38,
                background: `${theme.colors.alpha.black[100]}`,
                color: `${theme.colors.alpha.white[100]}`
              }}
            >
              D
            </Avatar>
          </ListItemAvatar>
          <ListItemText
            primaryTypographyProps={{
              variant: 'h5',
              color: `${theme.colors.alpha.black[100]}`
            }}
            primary="Writer’s Digest Daily"
          />
        </ListItemButton>
        <Divider />
        <ListItemButton>
          <ListItemAvatar>
            <Avatar
              sx={{ width: 38, height: 38 }}
              src="/static/images/logo/google.svg"
            />
          </ListItemAvatar>
          <ListItemText
            primaryTypographyProps={{
              variant: 'h5',
              color: `${theme.colors.alpha.black[100]}`
            }}
            primary="Google Developers"
          />
        </ListItemButton>
      </ListWrapper>
    </Card>
  );
}

export default PopularTags;
