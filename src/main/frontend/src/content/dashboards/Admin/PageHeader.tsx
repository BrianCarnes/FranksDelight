import { Typography, Avatar, Grid } from '@mui/material';
import { useTheme } from '@mui/material/styles';

function PageHeader() {
  const user = JSON.parse(localStorage.getItem('user') || '{}');

  const theme = useTheme();

  return (
    <Grid container alignItems="center">
      <Grid item>
        <Avatar
          sx={{
            mr: 2,
            width: theme.spacing(8),
            height: theme.spacing(8)
          }}
          variant="rounded"
          alt={user.firstName}
          src="/static/images/avatars/avatar1.jpg"
        />
      </Grid>
      <Grid item>
        <Typography variant="h3" component="h3" gutterBottom>
          Welcome, {user.firstName + " " + user.lastName}!
        </Typography>
        <Typography variant="subtitle2">
          Get your Glizzy with our curry mustard and garlic aioli!
        </Typography>
      </Grid>
    </Grid>
  );
}

export default PageHeader;
