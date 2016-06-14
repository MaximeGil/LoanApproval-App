<?php

require_once __DIR__.'/bootstrap.php';

use Silex\Provider\FormServiceProvider;

$app = new Silex\Application();


 $app['debug'] = true;

$app->register(new FormServiceProvider());
$app->register(new Silex\Provider\SessionServiceProvider());
 $app['session.test'] = true;
$app->register(new Silex\Provider\MonologServiceProvider(), array(
    'monolog.logfile' => 'php://stderr',
));
$app->register(new Silex\Provider\UrlGeneratorServiceProvider());
$app->register(new Silex\Provider\ValidatorServiceProvider());
$app->register(new Silex\Provider\TranslationServiceProvider(), array(
    'translator.domains' => array(),
));

$app->register(new Silex\Provider\TwigServiceProvider(), [
    'twig.path' => __DIR__.'/../views',
]);

$app->get('/', 'loanapproval\Controller\PageController::getHelloWorld');
$app->get('/appmanager', 'loanapproval\Controller\PageController::getAppManager');
$app->post('/add', 'loanapproval\Controller\PageController::addAccount');
$app->post('/delete', 'loanapproval\Controller\PageController::deleteAccount');
$app->post('/appmanager/add', 'loanapproval\Controller\PageController::addApp');
$app->post('/appmanager/delete', 'loanapproval\Controller\PageController::deleteApp');
$app->get('/approval', 'loanapproval\Controller\PageController::getApproval');
$app->post('/approval/post', 'loanapproval\Controller\PageController::postApproval');





return $app;
