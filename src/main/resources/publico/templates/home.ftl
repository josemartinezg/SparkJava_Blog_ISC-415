<!DOCTYPE html>
<!-- saved from url=(0050)https://getbootstrap.com/docs/4.3/examples/album/# -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/album/">

    <!-- Bootstrap core CSS -->
    <link href="./Album example · Bootstrap_files/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link href="/css/bootstrap.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

    <!-- Custom styles for this template -->
    <link href="./Album example · Bootstrap_files/album.css" rel="stylesheet">
</head>
<body>
<header>
    <div class="bg-dark collapse" id="navbarHeader" style="">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-md-7 py-4">
                    <h4 class="text-white">About</h4>
                    <p class="text-muted">El usuario es ${usuario.nombre}</p>
                    <form action="/hacerLogout" method="post"  enctype="application/x-www-form-urlencoded">
                        <button type="submit" class="btn btn-danger btn-block"> Log out</button>
                    </form>
                </div>
                <div class="col-sm-4 offset-md-1 py-4">
                    <h4 class="text-white">Contact</h4>
                    <ul class="list-unstyled">
                        <li><a href="https://getbootstrap.com/docs/4.3/examples/album/#" class="text-white">Follow on Twitter</a></li>
                        <li><a href="https://getbootstrap.com/docs/4.3/examples/album/#" class="text-white">Like on Facebook</a></li>
                        <li><a href="https://getbootstrap.com/docs/4.3/examples/album/#" class="text-white">Email me</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container d-flex justify-content-between">
            <a href="https://getbootstrap.com/docs/4.3/examples/album/#" class="navbar-brand d-flex align-items-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" aria-hidden="true" class="mr-2" viewBox="0 0 24 24" focusable="false"><path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"></path><circle cx="12" cy="13" r="4"></circle></svg>
                <strong>${usuario.username}</strong>
            </a>
            <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </div>
</header>

<main role="main">
    <section class="jumbotron text-center">
        <div class="card justify-content-center container">
            <div class="card-header">
                <h1 class="jumbotron-heading">Usuario</h1>
            </div>
            <div class="card-body">
                <p class="lead text-muted">${usuario.nombre}</p>
                <p>
                    <a href="/hacerLogout" class="btn btn-danger my-2">Log out</a>
                </p>
            </div>
        </div>
    </section>
</main>

</body></html>