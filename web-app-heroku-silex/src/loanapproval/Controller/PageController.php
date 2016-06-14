<?php

namespace loanapproval\Controller;

use GuzzleHttp\Client;
use Silex\Application;
use Symfony\Component\HttpFoundation\Request;
use loanapproval\Form\FormAccManager;
use loanapproval\Form\FormAppManager;
use loanapproval\Form\FormApproval;


class PageController
{
    public function getHelloWorld(Application $app, Request $request)
    {
        $newForm = new FormAccManager();
        $form = $newForm->createFormAdd($app);
        $form->handleRequest($request);
        
        $anotherForm = new FormAccManager();
        $form2 = $anotherForm->createFormDelete($app);
        $form2->handleRequest($request);
        
        $client = new \GuzzleHttp\Client(['base_uri' => 'http://loanapprovalkhettabgil.appspot.com/api/']);
        $response = $client->request('GET', 'accountmanager/accounts/', ['headers' => ['Accept' => 'application/json']]);
        $response = json_decode($response->getBody(), true);
        return $app['twig']->render('hello.twig.html', array('accmanager' => $response, 'form' => $form->createView(), 'form2' => $form2->createView()));
    }
    
    public function addAccount(Application $app, Request  $request)
    {
        $newForm = new FormAccManager();
        $form = $newForm->createFormAdd($app);
        $form->handleRequest($request);
        
        if ($form->isValid()) {
            $data = $form->getData();
            //$data = json_encode($data);
            $array = array( "name" => $data['name'], 
                "surname" => $data['surname'], 
                "account" => intval($data['account']), 
                "risk" => $data['risk']);
            
            $array = json_encode($array);

           $client = new \GuzzleHttp\Client(['base_uri' => 'http://loanapprovalkhettabgil.appspot.com/api/']);
           $response = $client->request('POST', 'accountmanager/account/', ['headers' => ['content-type' => 'application/json'], 'body' => $array]);
     
             return $app->redirect('/');
        }
    }
    
    public function deleteAccount(Application $app, Request $request)
    {
        $newForm = new FormAccManager();
        $form = $newForm->createFormDelete($app);
        $form->handleRequest($request);
        
        if ($form->isValid()) {
            
           $data = $form->getData();
           $client = new \GuzzleHttp\Client(['base_uri' => 'http://loanapprovalkhettabgil.appspot.com/api/']);
           $response = $client->request('DELETE', 'accountmanager/account/' . $data['name']);
     
           return $app->redirect('/');
        }
    }

    public function getLogin(Request $request, Application $app)
    {
        return $app['twig']->render('login.twig.html', array(
                    'error' => $app['security.last_error']($request),
                    'last_username' => $app['session']->get('_security.last_username'),
        ));
    }
    
    public function getAppManager(Request $request, Application $app)
    {
        $newForm = new FormAppManager();
        $form = $newForm->createFormAdd($app);
        $form->handleRequest($request);
        
        $anotherForm = new FormAppManager();
        $form2 = $anotherForm->createFormDelete($app);
        $form2->handleRequest($request);
        
        $client = new \GuzzleHttp\Client(['base_uri' => 'http://loanapprovalkhettabgil.appspot.com/api/']);
        $response = $client->request('GET', 'appmanager/approvals/', ['headers' => ['Accept' => 'application/json']]);
        $response = json_decode($response->getBody(), true);
        
        return $app['twig']->render('appmanager.twig.html', array('appmanager' => $response, 'form' => $form->createView(), 'form2' => $form2->createView()));
  
    }
    
    public function addApp(Request $request, Application $app)
    {
        $newForm = new FormAppManager();
        $form = $newForm->createFormAdd($app);
        $form->handleRequest($request);
        
        if ($form->isValid()) {
            $data = $form->getData();
            //$data = json_encode($data);
            $array = array( "name" => $data['name'], 
                "response" => $data['response'], 
                "amount" => intval($data['amount']));
            
            $array = json_encode($array);

           $client = new \GuzzleHttp\Client(['base_uri' => 'http://loanapprovalkhettabgil.appspot.com/api/']);
           $response = $client->request('POST', 'appmanager/approval/', ['headers' => ['content-type' => 'application/json'], 'body' => $array]);
     
             return $app->redirect('/index.php/appmanager');
        }
    }
    
    public function deleteApp(Request $request, Application $app)
    {
        $newForm = new FormAppManager();
        $form = $newForm->createFormDelete($app);
        $form->handleRequest($request);
        
        if ($form->isValid()) {
            
           $data = $form->getData();
           $client = new \GuzzleHttp\Client(['base_uri' => 'http://loanapprovalkhettabgil.appspot.com/api/']);
           $response = $client->request('DELETE', 'appmanager/approval/' . $data['name']);
     
           return $app->redirect('/index.php/appmanager');
        }
    }
    
    public function getApproval(Request $request, Application $app)
    {
        $newForm = new FormApproval();
        $form = $newForm->createForm($app);
        $form->handleRequest($request);
       
        return $app['twig']->render('approval.twig.html', array('form' => $form->createView()));
    }
    
    public function postApproval(Request $request, Application $app)
    {
        $newForm = new FormApproval();
        $form = $newForm->createForm($app);
        $form->handleRequest($request);
        
         if ($form->isValid()) {
            
           $data = $form->getData();
           $client = new \GuzzleHttp\Client(['base_uri' => 'http://morning-headland-94280.herokuapp.com/']);
           $response = $client->request('POST', 'loanapproval/approval/' . $data['name'] . '/' . $data['amount']);
     
          return $app['twig']->render('response.twig.html', array('response' => $response->getBody()));
  
        }
    }
}
