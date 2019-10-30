<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="assets/img/favicon.ico">
    <title>Mediumish</title>
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Fonts -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Righteous%7CMerriweather:300,300i,400,400i,700,700i" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="assets/css/mediumish.css" rel="stylesheet">
    <!-- Textarea -->
    <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
    <script>tinymce.init(
        {
            selector:'textarea#default',
            menubar: false // removes the menubar
        });
    </script>
</head>
<body>

<!-- Begin Nav
================================================== -->
<#include "*/navBar.ftl">
<!-- End Nav
================================================== -->
<div class="container">
    <div class="row justify-content-center">
        <div class="col">
            <form method="post" action="/postArticulo/">
                <div class="row">
                    <div class="col form-group">
                        <label for="title">Title</label>
                        <input id="title" name="title" class="form-control" placeholder="Title" type="text">
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="default">Content</label>
                        <textarea id="default" name="articulo">Write your Post here!</textarea>
                    </div>
                </div>
                <br/>
                <div class="row align-items-center">
                    <div class="col">
                        <button type="submit" class="btn btn-primary">Post</button>
                        <a href="/home" class="btn btn-danger">Cancel</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Begin Footer
================================================== -->
<div class="container">
    <div class="footer">
        <p class="pull-left">
            Copyright &copy; 2017 Your Website Name
        </p>
        <p class="pull-right">
            Mediumish Theme by <a target="_blank" href="https://www.wowthemes.net">WowThemes.net</a>
        </p>
        <div class="clearfix">
        </div>
    </div>
</div>
<!-- End Footer
================================================== -->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="assets/js/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
<script src="assets/js/mediumish.js"></script>
</body>
</html>
