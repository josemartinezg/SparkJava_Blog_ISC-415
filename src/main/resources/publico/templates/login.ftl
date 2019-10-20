<link href="/css/bootstrap.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<head>
    <title>${titulo}</title>
</head>
<div class="container">
    <br>  <p class="text-center">Primera Practica en Clase</p>
    <hr>

    <div class="row">
        <aside class="col-md-12">
            <div class="card">
                <article class="card-body">
                    <a href="" class="float-right btn btn-outline-primary">Sign up</a>
                    <h4 class="card-title mb-4 mt-1">Sign in</h4>
                    <form action="/hacerLogin/" method="post"  enctype="application/x-www-form-urlencoded">
                        <div class="form-group">
                            <label>Username</label>
                            <input name="username" class="form-control" placeholder="Username" type="text">
                        </div> <!-- form-group// -->
                        <div class="form-group">
                            <label> Password</label>
                            <input name="password" class="form-control" placeholder="******" type="password">
                        </div> <!-- form-group// -->
                        <div class="form-group">
                        </div> <!-- form-group// -->
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block"> Login  </button>
                        </div> <!-- form-group// -->
                    </form>
                </article>
            </div> <!-- card.// -->

        </aside> <!-- col.// -->

    </div> <!-- row.// -->

</div>
